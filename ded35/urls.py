from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('ded35.views',
    # Example:
    # (r'^rpgmanager_django/', include('rpgmanager_django.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # (r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    # (r'^admin/', include(admin.site.urls)),
    (r'^show/(?P<classe_id>\d+)/$', 'showClasse'),
    (r'^show/(?P<language_id>.+)/(?P<classe_id>\d+)/$', 'showClasse'),
    (r'^json/(?P<classe_id>\d+)/$', 'jsonClasse'),
)
