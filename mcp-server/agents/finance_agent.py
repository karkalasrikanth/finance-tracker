from langchain_core.prompts import ChatPromptTemplate
from langchain_core.tools import tool
from langchain_openai import ChatOpenAI
from langchain.agents import create_agent

from tools.analytics_tool import category_spend_analysis
from tools.budget_tool import get_budget_limits
from tools.transaction import fetch_transactions


def create_finance_chain(context):

    llm = ChatOpenAI(model="gpt-4o-mini", temperature=0)

    llm_with_tools = llm.bind_tools([
        fetch_transactions, 
        category_spend_analysis, 
        get_budget_limits
        ])
    
    prompt = ChatPromptTemplate.from_messages([
        ("system",
         "You are a financial assistant. "
         "Use tools to fetch user financial data and provide insights."),
        ("human",
         "User ID: {user_id}\nQuery: {query}")
    ])

    chain = (
        {
            "query": lambda _: context["query"],
            "user_id": lambda _: context["user_id"]
        }
        | prompt
        | llm_with_tools
    )

    return chain


