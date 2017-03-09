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

vcsrepo { "/app/src/":
    ensure   => latest,
    owner    => 'vagrant',
    group    => 'vagrant',
    provider => git,
    require  => [ Package["git"] ],
    source   => "https://github.com/felipeguerra19/clojure-websocket-react-geolocation.git",
    revision => 'master',
}