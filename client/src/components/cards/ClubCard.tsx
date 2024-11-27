import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { CalendarIcon } from 'lucide-react'
import Image from "next/image"
import { Club } from "@/types/club"
import { Badge } from "@/components/ui/badge"
import Link from "next/link"

interface ClubCardProps {
    club: Club;
}

export function ClubCard({ club }: ClubCardProps) {
    return (
        <Card className="flex flex-col h-full">
            <CardHeader>
                <div className="flex items-center space-x-4">
                    <div className="relative w-12 h-12">
                        <Image
                            src={club.logoUrl}
                            alt={`${club.name} logo`}
                            layout="fill"
                            objectFit="cover"
                            className="rounded-full"
                        />
                    </div>
                    <div>
                        <CardTitle className="mb-1">{club.name}</CardTitle>
                        <Badge variant="secondary">{club.teamType}</Badge>
                    </div>
                </div>
            </CardHeader>
            <CardContent className="flex-grow">
                <p className="text-muted-foreground">{club.description}</p>
                <div className="flex items-center mt-4 text-sm text-muted-foreground">
                    <CalendarIcon className="mr-2 h-4 w-4" aria-hidden="true" />
                    <span>Established {club.establishedDate}</span>
                </div>
            </CardContent>
            <CardFooter>
                <Button className="w-full" asChild>
                    <Link href={`/clubs/${club.id}`}>
                        View {club.name}
                        <span className="sr-only"> details</span>
                    </Link>
                </Button>
            </CardFooter>
        </Card>
    )
}

