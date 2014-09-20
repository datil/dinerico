###* @jsx React.DOM ###

nav = require('../../app/jsx/nav.jsx')

module.exports = () ->
  ReactTestUtils = Render = Simulate = null
  
  beforeEach () ->
    ReactTestUtils = React.addons.TestUtils
    Render = ReactTestUtils.renderIntoDocument
    Simulate = ReactTestUtils.Simulate

  it "Check Text Assignment", () ->
    nav = `<nav />`
    Render nav
    
    expect(nav).to.exist
    expect(nav.getDOMNode().innerHTML).to.equal 'Hello World'


