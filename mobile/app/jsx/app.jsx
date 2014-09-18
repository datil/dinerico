/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */

'use strict';
module.exports = React.createClass({
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
      <div>
        <p className="content-padded">          
        </p>
        <div className="row">
          <div className="col-md-3">
            <div className="transactions">
              <a href="/#deposito" className="btn btn-info btn-lg btn-block">Depósito</a>
              <a href="#modal" className="btn btn-primary btn-lg btn-block">Retiro</a>
              <button className="btn btn-default btn-lg btn-block">Envío</button>
            </div>
          </div>
          <div className="col-md-9">
            <h2>{this.props.summary}</h2>
            <div className="well">
              <h3>Efectivo en Caja</h3>
              <div>$1,900.93</div>
            </div>
          </div>
        </div>
      </div>
      /* jshint ignore:end */
    );
  }
});

