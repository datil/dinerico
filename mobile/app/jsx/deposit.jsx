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
        <form className="col-md-6 col-md-offset-3">
          <p className="instructions">Escriba los datos del destinatario</p>
          <hr/>
          <div className="form-group">
            <label htmlFor="beneficiary">Número de teléfono celular</label>
            <input type="tel" className="form-control" name="recipient" id="beneficiary" />
          </div>
          <div className="form-group">
            <label htmlFor="">Cédula</label>
            <input type="text" name="id" className="form-control"/>
          </div>
          <div className="form-group">
            <label for="">Valor</label>
            <input type="text" name="amount" className="form-control"/>
          </div>
          <div className="form-buttons centered">
            <a href="/#hacer-deposito" className="btn btn-primary btn-lg">Depositar</a>
          </div>
        </form>
      </div>
      /*jshint ignore:end */
    );
  }
});

