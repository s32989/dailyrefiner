<template>
  <div id="app">
    <div id="page">
      <table>
        <h1>World News</h1>
        <tr v-for="postData in postDataList" :key="postData.value" >
          <a v-if="postData.type=='world'"  v-bind:href="postData.url">{{ postData.title }}</a>
        </tr>
        <h1>National News</h1>
        <tr v-for="postData in postDataList" :key="postData.value" >
          <a v-if="postData.type=='national'"  v-bind:href="postData.url">{{ postData.title }}</a>
        </tr>
    </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
  data() {
    return {
      postDataList: [],
    };
  },

  methods: {
    async getDataConvertToSet() {
      const res = await fetch("http://127.0.0.1:8000/news");
      const finalRes = await res.json();
      const setNews = new Set(finalRes);
      this.postDataList = setNews;
    },
  },
  mounted() {
    this.getDataConvertToSet();
  },
};
</script>

<style>
@font-face {
  font-family: "Montserrat";
  src: local("Montserrat"),
    url(./fonts/Montserrat-VariableFont_wght.ttf) format("truetype");
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#page {
  border: 100px;
  border-style: solid;
  border-color: grey;
  box-shadow: inset 0px 0px 0px 6px;
  border-radius: 10px;
  padding: 10px;
}
a {
  font-family: "Montserrat";
}
</style>
