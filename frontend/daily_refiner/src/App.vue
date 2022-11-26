<template>
  <div id="app">
    <div id="page">
      <li v-for="url in urlList" :key="url">
        {{ url }}
      </li>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
  data() {
    return {
      urlList: [],
    };
  },

  methods: {
    async getData() {
      const res = await fetch("http://localhost:8080/getNews");
      const finalRes = await res.json();
      this.urlList = finalRes;
    },
  },
  mounted() {
    this.getData();
  },
};
</script>

<style>
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
}
</style>
