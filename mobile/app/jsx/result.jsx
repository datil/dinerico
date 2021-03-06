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
  send: function(e) {
    e.preventDefault();
    var $btn = $(this.refs.sendBtn.getDOMNode());
    $btn.button("loading");
    setTimeout(function() {
      $btn.button("reset");
      window.location = $btn.attr("href");
    }, 2000);
  },
  render: function () {
    return (
      /*jshint ignore:start */
      <div className="row app-msg">
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
          <div className="row nested">
            <div className="col-md-6">
              <div className="form-group">
                <label>Correo electrónico</label>
                <input className="form-control input-lg" type="email" />
              </div>
            </div>
            <div className="col-md-3 buttons">
              <a href="#recibo-enviado"
                 ref="sendBtn"
                 className="btn btn-primary btn-lg btn-block"
                 data-loading-text="Enviando"
                 onClick={this.send}>
                Enviar
              </a>&nbsp;&nbsp;&nbsp;
            </div>
            <div className="col-md-3 buttons">
              <a href="" className="btn btn-default btn-lg btn-block">¡No gracias!</a>
            </div>
          </div>
        </div>
      </div>
      /*jshint ignore:end */
    );
  }
});

