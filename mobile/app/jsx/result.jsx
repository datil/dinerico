/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */

'use strict';
module.exports = React.createClass({
  getDefaultProps: function() {
    return {
      title: "¡Transacción exitosa!",
      resultIcon: "icon-success",
      prompt: "¿Desea generar un comprobante de depósito?"
    }
  },
  render: function () {
    return (
      /*jshint ignore:start */
      <div className="row result">
        <div className="col-md-8 col-md-offset-2">
          <div className="message">
            <hr/>
            <div className={this.props.resultIcon}>&nbsp;</div>
            <h1 className="title">{this.props.title}</h1>
            <hr/>
            <p className="prompt">
              {this.props.prompt}
            </p>
          </div>
          <div className="row">
            <div className="col-md-6">
              <div className="form-group">
                <label>Correo electrónico</label>
                <input className="form-control input-lg" placeholder="Escriba el correo" />
              </div>
            </div>
            <div className="col-md-6 buttons">
              <a href="" className="btn btn-primary btn-lg">Enviar</a>&nbsp;&nbsp;&nbsp;
              <a href="" className="btn btn-default btn-lg">¡No gracias!</a>
            </div>
          </div>
        </div>
      </div>
      /*jshint ignore:end */
    );
  }
});

