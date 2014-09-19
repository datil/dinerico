/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */
'use strict';

dinerico.Views = dinerico.Views || {};

var app = require('./app.jsx');
var deposit = require('./deposit.jsx');
var doDeposit = require('./doDeposit.jsx');

// dinerico.Views.deposit = deposit;

dinerico.Views.app = function() {
  React.renderComponent(
    /* jshint ignore:start */
    <app />,
    document.getElementById('app')
    /* jshint ignore:end */
  );
}

dinerico.Views.deposit = function() {
  React.renderComponent(
    /* jshint ignore:start */
    <deposit />,
    document.getElementById('app')
    /* jshint ignore:end */
  );
}

dinerico.Views.doDeposit = function() {
  React.renderComponent(
    /* jshint ignore:start */
    <doDeposit />,
    document.getElementById('app')
    /* jshint ignore:end */
  );
}