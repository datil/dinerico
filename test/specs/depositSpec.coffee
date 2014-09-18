###* @jsx React.DOM ###

deposit = require('../../app/jsx/deposit.jsx')

module.exports = () ->
  ReactTestUtils = Render = Simulate = null
  
  beforeEach () ->
    ReactTestUtils = React.addons.TestUtils
    Render = ReactTestUtils.renderIntoDocument
    Simulate = ReactTestUtils.Simulate

  it "Check Text Assignment", () ->
    deposit = `<deposit />`
    Render deposit
    
    expect(deposit).to.exist
    expect(deposit.getDOMNode().innerHTML).to.equal 'Hello World'


