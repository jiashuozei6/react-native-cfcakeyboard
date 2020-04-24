require "json"
package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|

  s.name         = 'SipEditText'
  s.version      = package['version']
  s.summary      = package['description']
  s.license      = package['license']
  s.authors      = package['author']
  s.platforms    = { :ios => "9.0", :tvos => "9.2" }
  s.description  = <<-DESC
                    This is a CFCA Custom Keyboard Plugin
                     DESC
  s.homepage     = "https://github.com/jiashuozei6/react-native-cfcakeyboard"
  s.source       = { :git => "https://github.com/jiashuozei6/react-native-cfcakeyboard.git" }
  
  s.requires_arc = true
  s.source_files = "iOS/**/*.{h,m}"

  s.vendored_libraries  = 'iOS/SipEditText/**/*.{a}'
  s.resource_bundles = {
      'react-native-cfcakeyboard' => ['iOS/SipEditText/libs/*']
  }

  s.dependency 'React'
end
