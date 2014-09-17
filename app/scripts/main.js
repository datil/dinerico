(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */

'use strict';
module.exports = React.createClass({displayName: 'exports',
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
        React.DOM.p({ref: "p", className: "lead"}, this.state.message), 
        React.DOM.p(null, 
          React.DOM.button({
            type: "button", 
            className: "btn btn-success", 
            ref: "button", 
            onClick: this.reverse}, 
            React.DOM.span({className: "glyphicon glyphicon-refresh"}), 
              "Click me!"
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

var app = require('./app.jsx');

React.renderComponent(
  /* jshint ignore:start */
  app(null),
  document.getElementById('app')
  /* jshint ignore:end */
);

},{"./app.jsx":1}]},{},[2]);
