#!/usr/bin/perl

use Browser::Open qw( open_browser );

my $url = 'http://www.google.com/';
open_browser($url);