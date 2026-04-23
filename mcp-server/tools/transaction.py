from langchain_core.tools import tool

from db import get_connection


@tool
def fetch_transactions(user_id: int):
    """Fetch user transactions from DB"""
    conn = get_connection()
    cur = conn.cursor()
    cur.execute("""
            SELECT category, amount FROM TRANSACTIONS WHERE USER_ID = %s
                """, user_id)
    rows = cur.fetchall()
    return [{"category": r[0], "amount": float(r[1])} for r in rows]