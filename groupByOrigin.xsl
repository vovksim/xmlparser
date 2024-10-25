<?xml version="1.0"?>
<xsl:stylesheet version="1.0" exclude-result-prefixes="xsl" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" indent="yes"/>
    <xsl:key name="originCountry" match="Device" use="origin"/>

    <xsl:template match="Devices">
        <Devices>
            <xsl:apply-templates select="Device[generate-id(.) = generate-id(key('originCountry',origin))]"/>
        </Devices>
    </xsl:template>

    <xsl:template match="Device">
        <origin country="{origin}">
            <xsl:for-each select="key('originCountry',origin)">
                <Device id="{@id}">
                    <name>
                        <xsl:value-of select="name"/>
                    </name>
                    <price>
                        <xsl:value-of select="price"/>
                    </price>
                    <critical>
                        <xsl:value-of select="critical"/>
                    </critical>
                    <type>
                        <peripheral>
                            <xsl:value-of select="type/peripheral"/>
                        </peripheral>
                        <energyConsumption>
                            <xsl:value-of select="type/energyConsumption"/>
                        </energyConsumption>
                        <hasCooler>
                            <xsl:value-of select="type/hasCooler"/>
                        </hasCooler>
                        <group>
                            <xsl:value-of select="type/group"/>
                        </group>
                        <xsl:for-each select="type/port">
                            <port quantity="{@quantity}">
                                <xsl:value-of select="."/>
                            </port>
                        </xsl:for-each>
                    </type>
                </Device>
            </xsl:for-each>
        </origin>
    </xsl:template>

</xsl:stylesheet>
