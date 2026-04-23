from langchain_core.tools import tool

from db import get_connection


@tool
def get_budget_limits(user_id: int):
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        SELECT * FROM BUDGETS WHERE USER_ID = %s
                """, user_id)
    rows = cur.fetchall()
    return {row[0]: float(row[1]) for row in rows}