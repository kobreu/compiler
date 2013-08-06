require("conversion")

c = Conversion:new()

tree=Tree:new()
leafa=Tree:new()
leafb=Tree:new()

leafa:createLeaf('a')
leafb:createLeaf('b')
tree:setType(1)
tree:setChildLeft(leafa)
tree:setChildRight(leafb)

c:setInput(tree)

a=c:doConversion()

a:printAutomaton();
