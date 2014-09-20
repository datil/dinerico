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
      <div className="row">
        <div className="col-md-4 left-menu">
          <h2 className="welcome">¡Bienvenido!</h2>
          <hr/>
          <p>¿Qué <span className="accent">transacción</span> desea realizar?</p>
          <div className="transactions">
            <a href="#deposito" className="btn btn-default btn-lg btn-block">Depósito</a>
            <a href="#" className="btn btn-default btn-lg btn-block">Retiro</a>
            <a className="btn btn-default btn-lg btn-block">Envío</a>
          </div>
        </div>
        <div className="col-md-8 main-content">
          <div className="summary">
            <h3 className="title">{this.props.summary}</h3>
            <div className="well">
              <div className="amount">$12,000.55</div>
            </div>
            <div id="logo-bce">
            </div>
          </div>
        </div>
      </div>
      /* jshint ignore:end */
    );
  }
});

