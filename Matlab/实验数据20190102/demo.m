clear all;
close all;
% path = 'С��';
path = 'С��';
addpath(path);
data = load('postion2_circle2.txt');
epc_col=1;
unique(data(:,epc_col))
index=data(:,epc_col)==1;
plot(data(index,3))