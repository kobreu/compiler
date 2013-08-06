t=require("token")

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
return ((self.from == t.from) and (self.to == t.to) and (self.label == t.label));
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
trans = Transition:new()
trans:setFrom(f)
trans:setTo(t)
trans:setLabel(l)
self.delta = {next = self.delta, value = trans};
end

function Automaton:printStates()
print('states of the automaton:')
local iter = list_iter(self.states);
local el = iter()
while (el ~= nil) do
	print(el)
	el = iter()
end
end

function Automaton:printFinal()
print('final states:')
local iter = list_iter(self.finalStates)
local el = iter()
while (el~=nil) do
	print(el)
	el = iter()
	end
	end
	

function Automaton:printDelta()
print('Transitions:')
local iter = list_iter(self.delta);
local el = iter()
while (el~=nil) do
	el:printTransition()
	el = iter()
end
end	

function Automaton:printAutomaton()
self:printStates()
self:printFinal()
self:printDelta()
end