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
            <p>USD $350.00</p>
          </div>
          <hr/>
          <div className="form-group">
            <label htmlFor="pin">Introduzca el PIN:</label>
            <input type="number" className="form-control" id="PIN" />
          </div>
          <div className="form-buttons centered">
            <button className="btn btn-primary btn-lg">Confirmar</button>
          </div>
      </div>
      /*jshint ignore:end */
    );
  }
});

