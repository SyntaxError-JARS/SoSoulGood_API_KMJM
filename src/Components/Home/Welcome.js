import React from 'react'
import { useEffect, useState } from 'react'

function Menu() {
    const [menuBody, setMenuBody] = useState([])

    useEffect(() => {
        viewAll();
    }, []);

    async function viewAll(){
        try{

            const response = await fetch("https://SoSoulGood.azurewebsites.net/menu");
            const menu = await response.json();
            const menuTableRows = menu.map((e) =>) {
                return (
            <tr>
                    <td>{e.menuItem}</td>
            
            </tr>
                )
            }
        }
    }
}