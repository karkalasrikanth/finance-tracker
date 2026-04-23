from agents.finance_agent import create_finance_chain
from orchestrator.context_builder import build_context


def process_query(user_id: int, query: str):

    context = build_context(user_id=user_id, query=query)
    chain = create_finance_chain(context)
    response = chain.invoke({})
    return response.content
    