Tree = {}

function Tree:new()                       
  newObj = {root = nil}               
  self.__index = self                     
  return setmetatable(newObj, self)        
end

---------------------    Node DS    ----------------------

Node = {}

function Node:new()                       
  newObj = {key = nil, leftChild = nil, rightChild = nil, height = 0}               
  self.__index = self                     
  return setmetatable(newObj, self)        
end

local function getHeight(node)
	return node and node.height or 0
end

function Node:getBalance()
  local b = getHeight(self.rightChild) - getHeight(self.leftChild)
  return b  
end

local function setHeight(node)
	node.height = math.max(getHeight(node.leftChild),getHeight(node.rightChild)) + 1
end

----------------------   Helper functions   --------------------

-- sets child pointer of parent node 
local function setParentChild(oldNode, newNode, nodeParent)
   if nodeParent then 
    if nodeParent.rightChild == oldNode then
      nodeParent.rightChild = newNode
    else
      nodeParent.leftChild = newNode
    end
  end
end

-- set child accesses according to the given direction
local function setAccess(direction)
  thisChild = direction .. "Child" -- "leftChild" if direction equals "left", "rightChild" respectively
  otherChild = direction == "left" and "rightChild" or "leftChild"
  return thisChild, otherChild
end

-------------------    Rotation methods   --------------------

-- do simple rotation by updating pointers
local function simpleRotate(tree, node, direction, y, nodeParent)
  thisChild, otherChild = setAccess(direction)
  setParentChild(node, y, nodeParent)
  node[otherChild] = y[thisChild]
  y[thisChild] = node

  if node == tree.root then
    tree.root = y
  end
end

-- do double rotation by updating pointers
local function doubleRotate(tree, node, direction, y, x, nodeParent)
  thisChild, otherChild = setAccess(direction)
  setParentChild(node, x, nodeParent)
  node[otherChild] = x[thisChild]
  y[thisChild] = x[otherChild]
  x[thisChild] = node
  x[otherChild] = y
  setHeight(x)

  if node == tree.root then
    tree.root = x
  end
end

local function rotate(tree, node, nodeParent)
  balance = node:getBalance()
  if balance > 1 then 
    -- rotatel eft
    y = node.rightChild
    if y:getBalance() >= 0 then
      simpleRotate(tree, node, "left", y, nodeParent)
    else
      doubleRotate(tree, node, "left", y, y.leftChild, nodeParent)
    end
  elseif balance < -1 then
   --  rotate right
    y = node.leftChild
    if y:getBalance() <= 0 then
      simpleRotate(tree, node, "right", y, nodeParent)
    else
      doubleRotate(tree, node, "right", y, y.rightChild, nodeParent  )
    end
  else
    error("Trying to rotate node" .. node.key .. " with balance " .. balance)
  end
  setHeight(node)
  setHeight(y)
end

-------------------       More helper functions     ------------------

local function updateHeights(path)
  for i=#path,1, -1 do
    setHeight(path[i])
  end
end

-- check path to root for unbalances nodes and call rotate if necessary
local function rebalance(tree, key)
  local heightPath = {}
  -- update heights
  heightPath = tree:searchPath(key)
  updateHeights(heightPath)

  -- rebalance
  for i=#heightPath,1, -1 do
    if math.abs(heightPath[i]:getBalance()) > 1 then
      rotate(tree, heightPath[i], heightPath[i-1])
      -- keep heights updated
      updateHeights(tree:searchPath(key))
    end
  end
end

---------------------    AVL-Tree functions    ----------------------


-- inserts a node with a given key into the avl tree and returns the node if the insertion was successful
function Tree:insert(key)
  node = Node:new()
	node.key = key
	if self.root == nil then
		self.root = node
		node.height = 1
		return node
	end
	
  prevNode = self:search(key, self.root)
  if prevNode.key == key then
    return nil
  end
  
	-- insert node as a leaf
	node.height = 1
	if node.key < prevNode.key then
		prevNode.leftChild = node
	else 
    prevNode.rightChild = node
	end

  rebalance(self, prevNode.key)

  return node
end


-- returns the parent node to the given key, before possible rotations or the node itself if it already exists
-- binary search starts at "node"
function Tree:search(key, node, pred)
	if (node == nil) then 
		assert(pred)
		return pred
	end
	if (key == (node.key)) then 
		return node
	elseif (key < (node.key)) then 
		return self:search(key, node.leftChild, node)
	else
		return self:search(key, node.rightChild, node)
	end
end   

-- returns search path to given key
function Tree:searchPath(key)
  local path = {}
  local current = self.root 

  while current ~= nil do
    table.insert(path, current)     

    if key == current.key then
      break
    elseif key < current.key then 
      current = current.leftChild
    else
      current = current.rightChild
    end
  end
  return path
end

-- deletes the node with given key
function Tree:delete(key)
  local path = self:searchPath(key)
  local nodeParent = path[(#path) - 1]
  node = self:search(key, self.root)

  if node.key ~= key then
    error("Node "..node.key.." does not exist!")
    return
  end

  -- node is a leaf
  if node.leftChild == nil and node.rightChild == nil then
    -- node is root
    if node == self.root then
      self.root = nil
      return
    -- delete node
    else 
      setParentChild(node, nil, nodeParent)
    end
    tmp = nodeParent
  -- node has 1 child
  elseif node.leftChild == nil or node.rightChild == nil then
    child = node.leftChild or node.rightChild
    setParentChild(node, child, nodeParent)
    -- update root
    if node == self.root then
      self.root = child
      tmp = self.root
    else
      tmp = nodeParent
    end
   -- node has 2 children
  else
    -- search leftmost node in right subtree
    local leftMostChildPath = {}
    local current = node.rightChild

    repeat
      table.insert(leftMostChildPath, current)
      current = current.leftChild
    until current == nil

    local b = leftMostChildPath[#leftMostChildPath]
    local c = leftMostChildPath[#leftMostChildPath - 1]

    -- b is a leaf or has only a right subtree, replace node with b
    if c then
      c.leftChild = b.rightChild
      b.leftChild = node.leftChild
      b.rightChild = node.rightChild
      setParentChild(node, b, nodeParent)
    else
      b.leftChild = node.leftChild
      setParentChild(node, b, nodeParent)
    end

    -- update root
    if node == self.root then
      self.root = b
    end

    tmp = c or b
  end

  rebalance(self, tmp.key)
end

-----------------------    Output functions    -------------------

local function printTree(node, depth)
  
  if node.rightChild then printTree(node.rightChild, depth+1) end

  tab = ""
  for i=1,depth do
    tab = tab .. "  "
  end
  print(tab .. node.key)

  if node.leftChild then printTree(node.leftChild, depth+1) end

end

function Tree:printAVL()
  printTree(self.root, 0)
end
