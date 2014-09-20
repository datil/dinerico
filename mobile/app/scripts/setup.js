
window.dinerico = {
  Models: {},
  Collections: {},
  Routers: {},
  Views: {},
  init: function() {
    var navView = dinerico.Views.nav();
    dinerico.Routers.app = new dinerico.Routers.App();
    
    Backbone.history.on("all", function (r, router) {
      navView.setProps({hidden: (window.location.hash == "" ||
                                 window.location.hash == "#recibo-enviado")});
    });
    Backbone.history.start();
  }
}

$(document).ready(function() {
  dinerico.init();
});