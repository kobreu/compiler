
Transition={}

function Transition:new()
newObj={from = nil, to = nil, label = nil}
self.__index=self
return setmetatable(newObj,self)
end

function Transition:setFrom(f)
self.from = f
end

function Transition:setTo(t)
self.to = t
end

function Transition:setLabel(l)
self.label = l
end

function Transition:isEqual(t)
return self.from == t.from and self.to == t.to and self.label == t.label;
end

function Transition:printTransition()
print(self.from..'->'..self.to..':'..self.label)
end


Automaton={}

function Automaton:new()
newObj={initial = nil, states = nil, finalStates = nil, delta=nil}
self.__index=self
return setmetatable(newObj,self)
end


function Automaton:setInitial(i)
self.initial =i
end

function Automaton:addState(i)
self.states = {next = self.states, value = i};
end

function Automaton:addFinal(i)
self.finalStates = {next = self.finalStates, value = i};
end

function Automaton:addTransition(f,t,l)
t = Transition:new()
t.setFrom(f)
t.setTo(t)
t.setLabel(l)
self.delta = {next = self.delta, value = t};
end

function list_iter (l)
	return function ()
		if l~=nil then return l.value end
		l = l.next
	end
end

function Automaton:printStates()
print('states of the automaton:')
local iter = list_iter(self.states);
while true do
	local el = iter()
	if el == nil then break
	else print(el)
	end
end
end

function Automaton:printDelta()
print('Transitions:')
local iter = list_iter(self.delta);
while true do
	local el = iter()
	if el == nil then break
	else el:printTransition()
	end
end
end	

function Automaton:printAutomaton()
self:printStates()
self:printDelta()
end