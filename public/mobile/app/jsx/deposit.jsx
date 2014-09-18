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
        <form className="col-md-8 col-md-offset-2">
          <p className="lead">Especifique el monto y el monedero de destinos</p>
          <div className="form-group">
            <input type="tel" className="form-control" placeholder="Número celular destinatario" name="recipient"/>
          </div>
          <div className="form-group">
            <input type="text" placeholder="Valor" name="amount" className="form-control"/>
          </div>
          <div className="form-group">
            <textarea className="form-control" rows="5" placeholder="Descripción (opcional)"></textarea>
          </div>
          <button className="btn btn-primary btn-lg btn-block">Depositar</button>
        </form>
      </div>
      /*jshint ignore:end */
    );
  }
});

