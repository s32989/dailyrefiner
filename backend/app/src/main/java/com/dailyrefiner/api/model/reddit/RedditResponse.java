package com.dailyrefiner.api.model.reddit;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class RedditResponse {

    String kind;
    ResponseData data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }

    public class ResponseData{

        List<Post> children;

        public List<Post> getChildren() {
            return children;
        }

        public void setChildren(List<Post> children) {
            this.children = children;
        }

        public class Post{
            String kind;
            PostData data;

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public PostData getData() {
                return data;
            }

            public void setData(PostData data) {
                this.data = data;
            }

            public class PostData{
                String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

        }
    }
}
