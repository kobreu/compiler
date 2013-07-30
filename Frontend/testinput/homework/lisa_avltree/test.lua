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
delete = true

printStep(10)
printStep(5)
printStep(17)
printStep(3)
printStep(1)
printStep(4)
printStep(4, delete)
printStep(8)
printStep(2)
printStep(7)
printStep(6)
printStep(9)
printStep(2, delete)
printStep(1, delete)
printStep(8, delete)
printStep(10, delete)


