/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */

'use strict';
module.exports = React.createClass({
  getDefaultProps: function () {
    return {
      hidden: true
    };
  },
  render: function () {
    var cx = React.addons.classSet;
    var classes = cx({
      'btn': true,
      'btn-default': true,
      'hidden': this.props.hidden
    });
    return (
      /*jshint ignore:start */
      <a href="" className={classes}>
        <span className="glyphicon glyphicon-arrow-left"></span>
      </a>
      /*jshint ignore:end */
    );
  }
});

