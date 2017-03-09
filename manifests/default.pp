include apt

package { 'git-core':
  ensure => installed,
}

package { 'git':
    ensure => installed,
}

file { '/app/src/':
  ensure => 'directory',
  owner  => 'vagrant',
  group  => 'vagrant',
  mode   => '0750',
}

file { '/app/clojure/lein/bin':
  ensure => 'directory',
  owner  => 'vagrant',
  group  => 'vagrant',
  mode   => '0750',
}

vcsrepo { "/app/src/":
    ensure   => latest,
    owner    => 'vagrant',
    group    => 'vagrant',
    provider => git,
    require  => [ Package["git"] ],
    source   => "https://github.com/felipeguerra19/clojure-websocket-react-geolocation.git",
    revision => 'master',
}

exec{'retrieve_leiningen':
  command => "/usr/bin/wget -q https://raw.github.com/technomancy/leiningen/stable/bin/lein -O /app/clojure/lein/bin/lein",
  creates => "/app/clojure/lein/bin/lein",
}

file{'/app/clojure/lein/bin/lein':
  mode => 0755,
  require => Exec["retrieve_leiningen"],
}

file { "/usr/local/bin/lein": 
  ensure => symlink,
  target => '/app/clojure/lein/bin/lein',
}