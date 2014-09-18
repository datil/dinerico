/*global dinerico, Backbone*/

dinerico.Models = dinerico.Models || {};

(function () {
    'use strict';

    dinerico.Models.Transaction = Backbone.Model.extend({

        url: '',

        initialize: function() {
        },

        defaults: {
        },

        validate: function(attrs, options) {
        },

        parse: function(response, options)  {
            return response;
        }
    });

})();
