from fastapi import FastAPI
import services.news_service.reddit_news_service as rns

app = FastAPI()

@app.on_event("startup")
def fetch_news():
    print(rns.get_news())
@app.get("/")
async def root():
    return {"message": "fuck"}
