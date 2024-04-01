from jproperties import Properties

configs = Properties()

with open(r'C:\Users\s3298\PycharmProjects\dailyrefiner\configs\app-config.properties', 'rb') as config_file:
    configs.load(config_file)
    
reddit_user = configs.get("redditUser").data
reddit_pw = configs.get("redditPassword").data
world_news_url = configs.get("redditWorldNewsUrl").data
auth_url = configs.get("authUrl").data
reddit_client_id = configs.get("redditClientID").data
reddit_secret = configs.get("redditSecret").data
approved_urls = configs.get("approvedUrls").data.split(",")

