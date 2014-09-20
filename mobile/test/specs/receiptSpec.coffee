###* @jsx React.DOM ###

receipt = require('../../app/jsx/receipt.jsx')

module.exports = () ->
  ReactTestUtils = Render = Simulate = null
  
  beforeEach () ->
    ReactTestUtils = React.addons.TestUtils
    Render = ReactTestUtils.renderIntoDocument
    Simulate = ReactTestUtils.Simulate

  it "Check Text Assignment", () ->
    receipt = `<receipt />`
    Render receipt
    
    expect(receipt).to.exist
    expect(receipt.getDOMNode().innerHTML).to.equal 'Hello World'


