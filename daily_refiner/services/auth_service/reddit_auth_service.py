from requests.auth import HTTPBasicAuth
import requests
import configs.reddit_config as config
import services.auth_service as auth_service

def get_token():
    headers = {"User-Agent": "daily_refiner Client/0.1 by rimbygord2"}

    basic_auth = HTTPBasicAuth(config.reddit_client_id, config.reddit_secret)
    post_data = {
        "grant_type": "password",
        "username": config.reddit_user,
        "password": config.reddit_pw
    }

    response = requests.post(config.auth_url, auth=basic_auth, headers=headers,data=post_data )
    return response.json().get("access_token")
