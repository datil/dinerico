/*global dinerico, Backbone*/

dinerico.Routers = dinerico.Routers || {};

(function () {
  'use strict';

  dinerico.Routers.App = Backbone.Router.extend({
    routes: {
      "": "index",
      "deposito": "deposit",
      "resultado": "result",
      "retiro": "withdraw",
      "confirmar-deposito": "doDeposit"
    },
    index: function() {
      dinerico.Views.app();
    },
    deposit: function() {
      dinerico.Views.deposit();
    },
    withdraw: function() {
    },
    doDeposit: function() {
      dinerico.Views.doDeposit();
    },
    result: function() {
      dinerico.Views.result();
    }
  });

})();