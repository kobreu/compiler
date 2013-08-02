graph = require("avltree")

local function printStep(key, delete)
  if delete then
  	print("delete("..key.."):\n")
  	b:delete(key)
  else
  	print("insert("..key.."): \n")
  	b:insert(key)
  end
  b:printAVL()
  print("--------------")
end

b = Tree:new()
bla = true

printStep(10)
printStep(5)
printStep(17)
printStep(3)
printStep(1)
printStep(4)
printStep(4, bla)
printStep(8)
printStep(2)
printStep(7)
printStep(6)
printStep(9)
printStep(2, bla)
printStep(1, bla)
printStep(8, bla)
printStep(10, bla)


