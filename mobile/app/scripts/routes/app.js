/*global dinerico, Backbone*/

dinerico.Routers = dinerico.Routers || {};

(function () {
  'use strict';

  dinerico.Routers.App = Backbone.Router.extend({
    routes: {
      "": "index",
      "deposito": "deposit",
      "retiro": "withdraw"
    },
    index: function() {
      dinerico.Views.app();
    },
    deposit: function() {
      dinerico.Views.deposit();
    },
    withdraw: function() {
    }
  });

})();