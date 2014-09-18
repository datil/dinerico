
window.dinerico = {
  Models: {},
  Collections: {},
  Routers: {},
  Views: {},
  init: function() {
    dinerico.Routers.app = new dinerico.Routers.App();
    Backbone.history.start();
  }
}

$(document).ready(function() {
  dinerico.init();
});