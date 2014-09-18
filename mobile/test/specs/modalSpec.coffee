###* @jsx React.DOM ###

modal = require('../../app/jsx/modal.jsx')

module.exports = () ->
  ReactTestUtils = Render = Simulate = null
  
  beforeEach () ->
    ReactTestUtils = React.addons.TestUtils
    Render = ReactTestUtils.renderIntoDocument
    Simulate = ReactTestUtils.Simulate

  it "Check Text Assignment", () ->
    modal = `<modal />`
    Render modal
    
    expect(modal).to.exist
    expect(modal.getDOMNode().innerHTML).to.equal 'Hello World'


