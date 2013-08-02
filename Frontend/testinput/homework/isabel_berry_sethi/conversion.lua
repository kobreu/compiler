require 'token'
require 'tree'
require 'automaton'


Conversion ={}

function Conversion:new()
newObj={size = nil, empty = nil, first=nil, nex = nil, last = nil, leaves = nil, result=nil, input=nil}
self.__index=self
return setmetatable(newObj,self)
end

function Conversion:count(t)
t.number = self.size
self.size = self.size+1
if t.token == LEAF then self.leaves[self.size-1]=t.label
elseif t.token == EPSILON then self.leaves[self.size-1] = "epsilon"
else  
if t.childLeft ~= nil then self.count(t.childLeft)
end
if t.childRight ~= nil then self.count(t.childRight)
end
end
end

function Conversion:setInput(t)
self.input = t
self.count(t)
self.nex[t.number]=nil;
end;

function list_iter (l)
	return function ()
		if l~=nil then return l.value end
		l = l.next
	end
end

function Conversion:addList(l,i)
local iter = list_iter(l)
local exist = false
while true do
	local el = iter()
	if el=nil then break
	elseif el == i then exist = true;
	end
end
if not exist then l = {next = l, value =i} end
return l
end	

function Conversion:fusion(l1,l2)
local result = nil
local iter1 = list_iter(l1)
while true do
	local el = iter1()
	if el == nil then break 
	else result = {next = result, value = el}
	end
end
local iter2 = list_iter(l2)
while true do
	local el = iter2()
	if el = nil then break
	else result = self.add(result,el)
	end
end
return result
end

function Conversion:doEmpty(t)
local result = false
local tok = t.token
if tok == OR then
	local left = self:doEmpty(t.childLeft)
	local right = self:doEmpty(t.childRight)
	result = left or right
	self.empty[t.number] = result
elseif tok == AND then
	local left = self:doEmpty(t.childLeft)
	local right = self:doEmpty(t.childRight)
	result = left and right
	self.empty[t.number] = result
elseif tok == STAR then
	local left = self:doEmpty(t.childLeft)
	result = true
	self.empty[t.number] = result
elseif tok == MAYBE then
	local left = self:doEmpty(t.childLeft)
	result = true
	self.empty[t.number] = result
elseif tok == EPSILON then
	result = true
	self.empty[t.number] = result
elseif tok == LEAF then
	result = false
	self.empty[t.number] = result
end
return result
end

function Conversion:doFirst(t)
local result = nil
local tok = t.token
if tok == OR then
	left = self:doFirst(t.childLeft)
	right = self:doFirst(t.childRight)
	result = self:fusion(left,right)
	self.first[t.number]=result
elseif tok == AND then
	left = self:doFirst(t.childLeft)
	right = self:doFirst(t.childRight)
	if self.empty[t.number] then result = fusion(left,right)
	else result = left end
	self.first[t.number]=result
elseif tok == STAR then
	result = self:doFirst(t.childLeft)
	self.first[t.number]=result
elseif tok == MAYBE then
	result = self:doFirst(t.childLeft)
	self.first[t.number]=result
elseif tok == EPSILON then
	result = nil
	self.first[t.number] = result
elseif tok == LEAF then
	result = nil
	result = {next = result, value = t.number}
	self.first[t.number] = result
end
return result
end

function Conversion:doNext(t)
local tok = t.token
if tok == OR then
	self.nex[t.childLeft.number]=self.nex[t.number]
	self.nex[t.childRight.number] = self.nex[t.number]
	self:doNext(t.childLeft)
	self:doNext(t.childRight)
elseif tok == AND then
	self.nex[t.childRight.number]=self.nex[t.number]
	if self.empty[t.childRight.number] then self.nex[t.childLeft.number] = self:fusion(self.first[t.childRight.number],self.nex[t.number])
	else self.nex[t.childLeft.number] = self.first[t.childRight.number] end
	self:doNext(t.childLeft)
	self:doNext(t.childRight)
elseif tok == STAR then
	self.nex[t.childLeft.number] = self:fusion(self.first[t.childLeft.number],self.nex[t.number])
	self:doNext(t.childLeft)
elseif tok == MAYBE then
	self.nex[t.childLeft.number] = self.nex[t.number]
	self:doNext(t.childLeft)
end
end

function Conversion:doLast(t)
local result = nil
local tok = t.token
if tok == OR then
	local left = self:doLast(t.childLeft)
	local right = self:doLast(t.childRight)
	result = self:fusion(left,right)
	last[t.number] = result
elseif tok == AND then
	local left = self:doLast(t.childLeft)
	local right = self:doLast(t.childRight)
	if self.empty[t.childRight.number] then result = self:fusion(left,right)
	else result = right end
	last[t.number]=result
elseif tok == STAR then
	result = self:doLast(t.childLeft)
	last[t.number]=result
elseif tok == MAYBE then
	result = self:doLast(t.childLeft)
	last[t.number]=result
elseif tok == EPSILON then
	result = nil
	last[t.number]=result
elseif tok == LEAF then
	result = nil
	result = {next=result,value=t.number}
	last[t.number] = result
end
return result
end

function Conversion:makeAutomaton()
local a = Automaton:new()
local init = self.input.number
a:addState(init)
a:setInitial(init)
for i,v in ipairs(self.leaves) do
	a:addState(i)
	local iter = list_iter(self.nex[i])
	while true do
		local el = iter()
		if el = nil then break
		else a:addTransition(i,el,self.leaves[el]) end
	end
end
local iter = list_iter(self.first[init])
while true do
	local el = iter()
	if el = nil then break
	else a:addTransition(init,el,self.leaves[el]) end
end
local iter2 = list_iter(self.last[init])
while true do
	local el = iter2()
	if el = nil then break
	else a:addFinal(el) end
end
if self.empty[init] then a:addFinal(init) end
return a
end

function Conversion:doConversion()
self:doEmpty(self.input)
self:doFirst(self.input)
self:doNext(self.input)
self:doLast(self.input)
local a = self:makeAutomaton();
return a
end


