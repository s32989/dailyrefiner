from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
import services.news_service.reddit_news_service as rns


app = FastAPI()

origins = {
    "http://localhost:8080",
}
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.on_event("startup")
def fetch_news():
    print()


@app.get("/news")
async def root():
    return rns.get_news()
