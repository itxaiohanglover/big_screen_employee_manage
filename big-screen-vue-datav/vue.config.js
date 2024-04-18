const path = require('path')
const resolve = dir => {
  return path.join(__dirname, dir)
}
module.exports = {
  publicPath: './',
  chainWebpack: config => {
    config.resolve.alias
    .set("@", resolve("src"))
    .set("assets", resolve("src/assets"))
    .set("components", resolve("src/components"))
    .set("base", resolve("baseConfig"))
    .set("public", resolve("public"));
  },
}