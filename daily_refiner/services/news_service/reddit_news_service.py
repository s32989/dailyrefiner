import requests
import configs.reddit_config as r_config
import services.auth_service.reddit_auth_service as reddit_auth
from lxml import html
import json

def get_world_news():
    headers = {"Authorization": "Basic  reddit_auth.get_token() "}
    headers.update({"User-Agent": "daily_refiner Client/0.1 by rimbygord2"})
    resp = requests.get(url=r_config.world_news_url, headers=headers)
    news = []
    for dat in resp.json()['data']['children']:
        # filter news sources
        item = {"url": dat['data']['url'],
                "title": dat['data']['title'],
                "type": "world"}
        news.append(item)
    return news


def get_national_news():
    URL = "https://www.reddit.com/t/us_news/"
    page = requests.get(URL)
    tree = html.fromstring(page.content)
    news = []
    links = tree.xpath(
        "//shreddit-post/div[@class='flex']/descendant::a[@class='a']")
    titles = tree.xpath("//shreddit-post/@post-title")
    for idx, x in enumerate(links):
        item = {"url": links[idx].attrib["href"],
                "title": titles[idx].encode('latin1').decode('utf-8'),
                "type": "national"}
        news.append(item)
    return news


def get_news():
    news = get_world_news()
    news.extend(get_national_news())
    return news
