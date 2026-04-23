from fastapi import FastAPI
from pydantic import BaseModel

from orchestrator.mcp_engine import process_query

app = FastAPI()


class QueryRequest(BaseModel):
    user_id: int
    query: str

@app.post("/ask")
def post_query(request: QueryRequest):
    result = process_query(request.user_id, request.query)
    return {"response": result}

