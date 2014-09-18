/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */

'use strict';
module.exports = React.createClass({
  render: function () {
    return (
      /*jshint ignore:start */
      <div id="modal" className="transaction">
        <header className="bar bar-nav">
          <a className="icon icon-close pull-right" href="#modal"></a>
          <h1 className="title">{this.props.title}</h1>
        </header>

        <div className="content">
          {this.props.children}
        </div>
      </div>
     /*jshint ignore:end */
    );
  }
});

