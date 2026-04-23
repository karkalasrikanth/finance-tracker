from langchain_core.prompts import ChatPromptTemplate
from langchain_openai import ChatOpenAI

from tools.analytics_tool import category_spend_analysis
from tools.budget_tool import get_budget_limits
from tools.transaction import fetch_transactions


def build_chain():
    llm = ChatOpenAI(model="gpt-4o-mini", temperature=0)
    llm_with_tool = llm.bind_tools([
        fetch_transactions,
        category_spend_analysis,
        get_budget_limits
    ])

    prompt = ChatPromptTemplate.from_messages([
        ("system",
         "You are a financial AI assistant. Use tools when needed."),
        ("human",
         "User ID: {user_id}\nQuery: {query}")
    ])

    chain = prompt | llm_with_tool

    return chain
