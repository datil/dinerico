(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */

'use strict';
module.exports = React.createClass({displayName: 'exports',
  getDefaultProps: function () {
    return {
      summary: "Resumen"
    };
  },
  getInitialState: function () {
    return {
      message : 'Always a pleasure scaffolding your apps.'
    };
  },
  reverse: function (event) {
    this.setState({
      message : this.state.message.split('').reverse().join('')
    });
  },
  render: function () {
    return (
      /* jshint ignore:start */
      React.DOM.div({className: "row"}, 
        React.DOM.div({className: "col-md-4 left-menu"}, 
          React.DOM.h2({className: "welcome"}, "¡Bienvenido!"), 
          React.DOM.hr(null), 
          React.DOM.p(null, "¿Qué ", React.DOM.span({className: "accent"}, "transacción"), " desea realizar?"), 
          React.DOM.div({className: "transactions"}, 
            React.DOM.a({href: "/#deposito", className: "btn btn-default btn-lg btn-block"}, "Depósito"), 
            React.DOM.a({href: "#modal", className: "btn btn-default btn-lg btn-block"}, "Retiro"), 
            React.DOM.a({className: "btn btn-default btn-lg btn-block"}, "Envío")
          )
        ), 
        React.DOM.div({className: "col-md-8 main-content"}, 
          React.DOM.div({className: "summary"}, 
            React.DOM.h3({className: "title"}, this.props.summary), 
            React.DOM.div({className: "well"}, 
              React.DOM.div({className: "amount"}, "$12,000.55")
            ), 
            React.DOM.div({id: "logo-bce"}
            )
          )
        )
      )
      /* jshint ignore:end */
    );
  }
});


},{}],2:[function(require,module,exports){
/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */

'use strict';

module.exports = React.createClass({displayName: 'exports',
  getDefaultProps: function () {
    return {
    };
  },
  render: function () {
    return (
      /*jshint ignore:start */
      React.DOM.div({className: "row"}, 
        React.DOM.form({className: "col-md-6 col-md-offset-3"}, 
          React.DOM.p({className: "instructions"}, 
            "Escriba los datos del ", React.DOM.strong(null, "destinatario")
          ), 
          React.DOM.hr(null), 
          React.DOM.div({className: "form-group"}, 
            React.DOM.label({htmlFor: "beneficiary"}, "Número de teléfono celular"), 
            React.DOM.input({type: "tel", className: "form-control", name: "recipient", id: "beneficiary"})
          ), 
          React.DOM.div({className: "form-group"}, 
            React.DOM.label({htmlFor: ""}, "Cédula"), 
            React.DOM.input({type: "text", name: "id", className: "form-control"})
          ), 
          React.DOM.div({className: "form-group"}, 
            React.DOM.label({for: ""}, "Valor"), 
            React.DOM.input({type: "text", name: "amount", className: "form-control"})
          ), 
          React.DOM.div({className: "form-buttons centered"}, 
            React.DOM.a({href: "/#confirmar-deposito", className: "btn btn-primary btn-lg"}, "Depositar")
          )
        )
      )
      /*jshint ignore:end */
    );
  }
});


},{}],3:[function(require,module,exports){
/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */
'use strict';

dinerico.Views = dinerico.Views || {};

var app = require('./app.jsx');
var deposit = require('./deposit.jsx');

// dinerico.Views.deposit = deposit;

dinerico.Views.app = function() {
  React.renderComponent(
    /* jshint ignore:start */
    app(null),
    document.getElementById('app')
    /* jshint ignore:end */
  );
}

dinerico.Views.deposit = function() {
  React.renderComponent(
    /* jshint ignore:start */
    deposit(null),
    document.getElementById('app')
    /* jshint ignore:end */
  );
}
},{"./app.jsx":1,"./deposit.jsx":2}]},{},[3]);
