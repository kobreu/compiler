require("token")

Tree ={}

function Tree:new()
newObj={token = nil, number = nil, childLeft=nil, childRight = nil, label = nil}
self.__index=self
return setmetatable(newObj,self)
end

function Tree:toString()
return self.label
end

function Tree:setNumber(i)
self.number=i;
end

function Tree:createLeaf(s)
self.token = LEAF;
self.label=s;
end

function Tree:setType(i)
if ((i == OR) or (i==AND) or (i==STAR) or (i==MAYBE)) then self.token = i;
else error('You are trying to create an unknown type of node');
end
end

function Tree:setChildLeft(c)
self.childLeft=c;
end

function Tree:setChildRight(c)
self.childRight = c;
end