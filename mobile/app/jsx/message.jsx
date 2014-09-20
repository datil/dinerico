/** @jsx React.DOM */
/*jshint indent: 2, node: true, nomen: true, browser: true*/
/*global React */

'use strict';
module.exports = React.createClass({
  getDefaultProps: function() {
    return {
      title: "Correo enviado",
      icon: "icon-email-sent",
      message: "¡Que tenga un buen día!"
    }
  },
  render: function () {
    return (
      /*jshint ignore:start */
      <div className="row app-msg">
        <div className="col-md-8 col-md-offset-2">
          <div className="message">
            <hr/>
            <div className={this.props.icon}>&nbsp;</div>
            <h1 className="title">{this.props.title}</h1>
            <hr/>
            <p className="submessage">
              {this.props.message}
            </p>
          </div>
          <div className="buttons" style={{textAlign: "center"}}>
            <a href="#" className="btn btn-default btn-lg">Inicio</a>
          </div>
        </div>
      </div>
      /*jshint ignore:end */
    );
  }
});

