/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */

'use strict';

module.exports = React.createClass({
  getDefaultProps: function () {
    return {
    };
  },
  render: function () {
    return (
      /*jshint ignore:start */
      <div className="row">
        <div className ="col-md-6 col-md-offset-3">
            <p className="instructions">Confirme los datos del depósito</p>
            <hr/>
            <div className="form-group">
              <label htmlFor="beneficiary">Destinatario:</label>
              <p>Joseph León Cando</p>
            </div>
            <div className="form-group">
              <label htmlFor="">Número de teléfono celular:</label>
              <p>0939125217</p>
            </div>
            <div className="form-group">
              <label htmlFor="">Número de cédula:</label>
              <p>0929128423</p>
            </div>
            <div className="form-group">
              <label htmlFor="">VALOR:</label>
              <p>$350.00</p>
            </div>
            <hr/>
            <div className="form-group" id="label-pin">
              <label htmlFor="pin">Introduzca el PIN:</label>
            </div>
            <div className="row">
              <div className="col-md-6">
                <input type="text" className="form-control" id="pin" />
              </div>
              <div className="col-md-6">
                <a href="#resultado" className="btn btn-primary btn-lg" id ="confirm">Confirmar</a>
              </div>
            </div>
        </div>
      </div>
      /*jshint ignore:end */
    );
  }
});

