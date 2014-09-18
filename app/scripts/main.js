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
      React.DOM.div(null, 
        React.DOM.p({className: "content-padded"}
        ), 
        React.DOM.div({className: "row"}, 
          React.DOM.div({className: "col-md-3"}, 
            React.DOM.div({className: "transactions"}, 
              React.DOM.a({href: "/#deposito", className: "btn btn-info btn-lg btn-block"}, "Depósito"), 
              React.DOM.a({href: "#modal", className: "btn btn-primary btn-lg btn-block"}, "Retiro"), 
              React.DOM.button({className: "btn btn-default btn-lg btn-block"}, "Envío")
            )
          ), 
          React.DOM.div({className: "col-md-9"}, 
            React.DOM.h2(null, this.props.summary), 
            React.DOM.div({className: "well"}, 
              React.DOM.h3(null, "Efectivo en Caja"), 
              React.DOM.div(null, "$1,900.93")
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
        React.DOM.form({className: "col-md-8 col-md-offset-2"}, 
          React.DOM.p({className: "lead"}, "Especifique el monto y el monedero de destinos"), 
          React.DOM.div({className: "form-group"}, 
            React.DOM.input({type: "tel", className: "form-control", placeholder: "Número celular destinatario", name: "recipient"})
          ), 
          React.DOM.div({className: "form-group"}, 
            React.DOM.input({type: "text", placeholder: "Valor", name: "amount", className: "form-control"})
          ), 
          React.DOM.div({className: "form-group"}, 
            React.DOM.textarea({className: "form-control", rows: "5", placeholder: "Descripción (opcional)"})
          ), 
          React.DOM.button({className: "btn btn-primary btn-lg btn-block"}, "Depositar")
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
