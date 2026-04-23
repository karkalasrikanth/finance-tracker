from collections import defaultdict

from langchain_core.tools import tool


@tool
def category_spend_analysis(transactions: list):
    """Analyze spending by category"""
    totals = defaultdict(float)
    for t in transactions:
        totals[t["category"]] += t["amount"]
    return dict(totals)


